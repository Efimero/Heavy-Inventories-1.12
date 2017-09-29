package superscary.heavyinventories.client.event;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import superscary.heavyinventories.calc.PlayerWeightCalculator;
import superscary.heavyinventories.client.gui.InventoryWeightText;
import superscary.heavyinventories.common.capability.offsets.IOffset;
import superscary.heavyinventories.common.capability.offsets.OffsetProvider;
import superscary.heavyinventories.common.capability.weight.IWeighable;
import superscary.heavyinventories.common.capability.weight.WeightProvider;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;
import superscary.heavyinventories.configs.reader.ConfigReader;
import superscary.heavyinventories.configs.weights.CustomConfigLoader;
import superscary.heavyinventories.util.Toolkit;
import superscary.supercore.tools.EnumColor;

/**
 * Copyright (c) 2017 by SuperScary(ERBF) http://codesynced.com
 * <p>
 * All rights reserved. No part of this software may be reproduced,
 * distributed, or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods,
 * without the prior written permission of the publisher, except in
 * the case of brief quotations embodied in critical reviews and
 * certain other noncommercial uses permitted by copyright law.
 */
public class ClientEventHandler
{

	/**
	 * For adding the weights to the items tooltip
	 * @param event
	 */
	@SubscribeEvent
	public void mouseOverTooltip(ItemTooltipEvent event)
	{
		if (HeavyInventoriesConfig.isEnabled)
		{
			ItemStack stack = event.getItemStack();
			if (stack != null)
			{
				//TODO: FIX - The itemstack is returning null on initialization
				if (stack.getItem().getRegistryName().toString().split(":")[0].equalsIgnoreCase("minecraft"))
				{
					double weight = PlayerWeightCalculator.getWeight(stack);
					event.getToolTip().add(form(weight));
					if (stack.getCount() > 1)
					{
						event.getToolTip().add(I18n.format("hi.gui.weight") + " " + (weight * stack.getCount()) + " Stone");
					}

					if (Minecraft.getMinecraft().currentScreen != null)
					{
						if (tooltipKeyCheck())
						{
							event.getToolTip()
								 .add(I18n.format("hi.gui.maxStackWeight", stack.getMaxStackSize()) + " " + (weight * stack
										 .getMaxStackSize()) + " Stone");
						}
						else
						{
							event.getToolTip().add(I18n.format("hi.gui.shift", EnumColor.YELLOW + "SHIFT" + EnumColor.GREY));
						}
					}
				}
				else
				{
					/**
					 * Custom reader
					 */
					//System.out.println(Toolkit.getModNameFromItem(stack.getItem()));
					//System.out.println(ConfigReader.getLoadedMods());
					if (ConfigReader.getLoadedMods().contains(Toolkit.getModNameFromItem(stack.getItem()) + ".cfg"))
					{
						String modid = Toolkit.getModNameFromItem(stack.getItem());
						//System.out.println("Config Reader contained: " + modid);

						double weight = CustomConfigLoader.getItemWeight(modid, stack.getItem());
						event.getToolTip().add(form(weight));
						if (stack.getCount() > 1)
						{
							event.getToolTip().add(I18n.format("hi.gui.weight") + " " + (weight * stack.getCount()) + " Stone");
						}

						if (Minecraft.getMinecraft().currentScreen != null)
						{
							if (tooltipKeyCheck())
							{
								event.getToolTip()
									 .add(I18n.format("hi.gui.maxStackWeight", stack.getMaxStackSize()) + " " + (weight * stack
											 .getMaxStackSize()) + " Stone");
							}
							else
							{
								event.getToolTip().add(I18n.format("hi.gui.shift", EnumColor.YELLOW + "SHIFT" + EnumColor.GREY));
							}
						}
					}
				}
			}
		}
	}

	private boolean tooltipKeyCheck()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
	}

	private String form(double weight)
	{
		return ChatFormatting.BOLD + "" + ChatFormatting.WHITE + "Weight: " + weight + " Stone";
	}

	private boolean encumberedMessage = false;
	private boolean overEncumberedMessage = false;

	/**
	 * Sends message if the player is encumbered
	 * @param event
	 */
	@SubscribeEvent
	public void handleEncumberance(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		weighable.setWeight(PlayerWeightCalculator.calculateWeight(player));

		if (weighable.getRelativeWeight() >= 1.0)
		{
			weighable.setOverEncumbered(true);
			encumberedMessage = false;

			if (!overEncumberedMessage)
			{
				//HeavyInventories.getNetwork().sendToServer(new PlayerOverEncumberedMessage(true));
				player.sendMessage(new TextComponentTranslation("hi.splash.overEncumbered"));
				overEncumberedMessage = true;
			}
		}
		else if (weighable.getRelativeWeight() < 1.0 && weighable.getRelativeWeight() >= 0.85)
		{
			weighable.setEncumbered(true);
			weighable.setOverEncumbered(false);

			if (!encumberedMessage)
			{
				//HeavyInventories.getNetwork().sendToServer(new PlayerEncumberedMessage(true));
				player.sendMessage(new TextComponentTranslation("hi.splash.weightWarning"));
				encumberedMessage = true;
			}
			overEncumberedMessage = false;
		}
		else
		{
			weighable.setEncumbered(false);
			encumberedMessage = false;
			overEncumberedMessage = false;

			//HeavyInventories.getNetwork().sendToServer(new PlayerNotEncumberedMessage(true));
		}
	}

	/**
	 * Checks if the player is encumbered and fixes whether or not they can move (and changes their move speed)
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerMove(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		weighable.setWeight(PlayerWeightCalculator.calculateWeight(player));

		if (!player.isCreative())
		{
			reduceSpeed(weighable, player);
		}

		if (HeavyInventoriesConfig.allowInCreative == true)
		{
			reduceSpeed(weighable, player);
		}
	}

	/**
	 * Method for changing players move speed
	 * @param weighable
	 * @param player
	 */
	public void reduceSpeed(IWeighable weighable, EntityPlayer player)
	{
		if (weighable.isOverEncumbered())
		{
			player.capabilities.setPlayerWalkSpeed(HeavyInventoriesConfig.overEncumberedSpeed);
		}
		else if (weighable.isEncumbered())
		{
			player.capabilities.setPlayerWalkSpeed(HeavyInventoriesConfig.encumberedSpeed / 10);
		}
		else
		{
			player.capabilities.setPlayerWalkSpeed((float) 0.1);
		}
	}

	/**
	 * Allows or disallows the player to jump based on encumberance
	 * @param event
	 */
	@SubscribeEvent
	public void livingJump(LivingEvent.LivingJumpEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();
			IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);

			if (!player.isCreative())
			{
				disableJumping(weighable, player);
				player.addExhaustion(1f);
			}

			if (HeavyInventoriesConfig.allowInCreative && player.isCreative() && weighable.isOverEncumbered())
			{
				disableJumping(weighable, player);
			}
		}
	}

	/**
	 * Method to stop the player from jumping
	 * @param weighable
	 * @param player
	 */
	@SideOnly(Side.CLIENT)
	public void disableJumping(IWeighable weighable, EntityPlayer player)
	{
		if (weighable.isOverEncumbered())
		{
			player.sendMessage(new TextComponentTranslation("hi.splash.noJump"));
			player.motionY *= 0D;
		}
		else if (weighable.isEncumbered())
		{
			player.sendMessage(new TextComponentTranslation("hi.splash.noJumpEncumbered"));
			player.motionY /= 5;
		}
	}

	/**
	 * Allows/disallows the player from sleeping (checks config)
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerSleep(PlayerSleepInBedEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		if (!HeavyInventoriesConfig.canSleepWhileOverEncumbered && weighable.isOverEncumbered())
		{
			event.setResult(EntityPlayer.SleepResult.OTHER_PROBLEM);
			event.getEntityPlayer().sendMessage(new TextComponentTranslation("hi.splash.loseWeightMax"));
		}
		else if (!HeavyInventoriesConfig.canSleepWhileEncumbered && weighable.isEncumbered())
		{
			event.setResult(EntityPlayer.SleepResult.OTHER_PROBLEM);
			event.getEntityPlayer().sendMessage(new TextComponentTranslation("hi.splash.loseWeight"));
		}
	}

	/**
	 * Transfers weight to a new player when they die (for mods that allow the player to keep their inventory
	 * on death)
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event)
	{
		EntityPlayer player = event.getEntityPlayer();
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		IWeighable weighableOld = event.getOriginal().getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		IOffset offset = player.getCapability(OffsetProvider.OFFSET_CAPABILITY, null);
		IOffset offsetOld = event.getOriginal().getCapability(OffsetProvider.OFFSET_CAPABILITY, null);

		weighable.setWeight(weighableOld.getWeight());
		offset.setOffset(offsetOld.getOffset());
	}

	/**
	 * Draws the on screen elements
	 * @param event
	 */
	@SubscribeEvent
	public void renderHUD(RenderGameOverlayEvent event)
	{
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
		{
			if (HeavyInventoriesConfig.allowInCreative || !Minecraft.getMinecraft().player.isCreative())
			{
				InventoryWeightText.renderText(Minecraft.getMinecraft());
			}
		}
	}

	/**
	 * Modifies how well a player can attack if they are encumbered
	 * @param event
	 */
	@SubscribeEvent
	public void attackStamina(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);

		if (weighable.isOverEncumbered() && isPlayerMoving(player))
		{
			player.addExhaustion(1f);
		}
		else if (weighable.isEncumbered() && isPlayerMoving(player))
		{
			player.addExhaustion(.03f);
		}
	}

	private static boolean sendRunMessage;

	/**
	 * Tick event to change if the player can run
	 * @param event
	 */
	@SubscribeEvent
	public void playerRun(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);

		if (player.isSprinting() && !player.isCreative() && (weighable.isOverEncumbered() || weighable.isEncumbered()))
		{
			player.setSprinting(canPlayerRun(player));

			if (!sendRunMessage && canPlayerRun(player))
			{
				player.sendMessage(new TextComponentTranslation("hi.splash.noRun"));
				sendRunMessage = true;
			}
		}
		else if (!player.isSprinting() && sendRunMessage)
		{
			sendRunMessage = false;
		}
	}

	/**
	 * Checks if player is moving
	 * @param player
	 * @return true if player is moving
	 */
	public static boolean isPlayerMoving(EntityPlayer player)
	{
		return player.moveForward != 0 || player.moveStrafing != 0 || player.moveVertical != 0;
	}

	/**
	 * Checks if the player can move based on weight
	 * @param player
	 * @return
	 */
	public static boolean canPlayerRun(EntityPlayer player)
	{
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		return !weighable.isEncumbered() || !weighable.isOverEncumbered();
	}

	// TODO fix where the player teleports. It is supposed to teleport them half the distance (with an enderpearl)
	/*@SubscribeEvent
	public void throwEnderPearl(EnderTeleportEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();
			IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);

			if (weighable.isOverEncumbered() || weighable.isEncumbered())
			{
				player.sendMessage(new TextComponentTranslation("hi.splash.noTeleport"));
				event.setTargetX((player.getPosition().getX() - event.getTargetX()) / 2);
				event.setTargetZ((player.getPosition().getZ() - event.getTargetZ()) / 2);
			}
		}
	}*/


	// TODO FIX: Creates loop on use of potion / doesn't affect max weight
	private static boolean hasUsedStrengthPotion;
	private static boolean hasUsedWeaknessPotion;
	@SubscribeEvent
	public void affectedByPotion(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;

		//Strength
		if (player.isPotionActive(Potion.getPotionById(5)))
		{
			if (!hasUsedStrengthPotion)
			{
				IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
				double weight = weighable.getMaxWeight();
				double newWeight = weight * .3d;

				if (!(weighable.getMaxWeight() == newWeight))
				{
					weighable.setMaxWeight(newWeight);
				}
				hasUsedStrengthPotion = true;
			}
		}
		else if (!player.isPotionActive(Potion.getPotionById(5)) && hasUsedStrengthPotion)
		{
			IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
			hasUsedStrengthPotion = false;
			weighable.setMaxWeight(HeavyInventoriesConfig.maxCarryWeight);
		}

		//Weakness
		if (player.isPotionActive(Potion.getPotionById(18)))
		{
			if (!hasUsedWeaknessPotion)
			{
				IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
				double weight = weighable.getMaxWeight();
				double newWeight = weight / .3d;

				if (!(weighable.getMaxWeight() == newWeight))
				{
					weighable.setMaxWeight(weighable.getMaxWeight() / .3); //30% decrease
				}
				hasUsedWeaknessPotion = true;
			}
		}
		else if (!player.isPotionActive(Potion.getPotionById(18)) && hasUsedWeaknessPotion)
		{
			IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
			hasUsedWeaknessPotion = false;
			weighable.setMaxWeight(HeavyInventoriesConfig.maxCarryWeight);
		}

	}

	@SubscribeEvent
	public void getPlayerWeightOffset(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		IOffset offset = player.getCapability(OffsetProvider.OFFSET_CAPABILITY, null);
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);

		weighable.setMaxWeight(HeavyInventoriesConfig.maxCarryWeight + offset.getOffset());
	}

	@SubscribeEvent
	public void savePlayerWeightOffset(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event)
	{
		EntityPlayer player = event.player;
		IOffset offset = player.getCapability(OffsetProvider.OFFSET_CAPABILITY, null);
		offset.setOffset(offset.getOffset());
	}

}
