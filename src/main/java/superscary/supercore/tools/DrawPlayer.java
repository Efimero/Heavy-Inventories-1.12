package superscary.supercore.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Copyright (c) 2017 SuperScary(ERBF) http://codesynced.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@SuppressWarnings("unused")
public class DrawPlayer
{

	/**
	 * Player follows mouse and can be rotated
	 *
	 * @param minecraft minecraft instance
	 * @param par1      x position
	 * @param par2      y position
	 * @param par3      scale
	 * @param par4
	 * @param par5
	 * @param mouseX    mouse X
	 * @param mouseY    mouse Y
	 * @param rotation  rotation the player should be rendered at
	 * @param player    player instance
	 */
	@SuppressWarnings("static-access")
	public static void drawPlayerRotatableTracking(Minecraft minecraft, int par1, int par2, int par3, float par4,
			float par5, int mouseX, int mouseY, float rotation, EntityPlayer player)
	{
		player.getActiveItemStack().setCount(0);
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();

		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GL11.glTranslatef(par1, par2, 50.0F);
		GL11.glScalef(-par3, par3, par3);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = player.renderYawOffset;
		float f3 = player.rotationYaw;
		float f4 = player.rotationPitch;
		float f5 = player.rotationYawHead;
		float f6 = player.prevRotationYawHead;
		par4 -= 19;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);

		player.rotationYawHead = minecraft.player.rotationYaw;
		player.rotationPitch = (float) Math.sin(minecraft.getSystemTime() / 500.0F) * 3.0F;
		GL11.glTranslatef(0.0F, (float) player.getYOffset(), 0.0F);
		rm.playerViewY = 180.0F;

		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-((float) Math.atan((double) (mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		player.renderYawOffset =
				rotation + ((float) Math.atan((double) (checkRotation(mouseX, rotation) / 40.0F)) * 20.0F);
		player.rotationYaw = (float) Math.atan(par4 / 40.0F) * 40.0F;
		player.rotationYaw = rotation + ((float) Math.atan((double) (checkRotation(mouseX, rotation) / 40.0F)) *
				40.0F);

		player.rotationPitch = -((float) Math.atan((double) (mouseY / 40.0F))) * 20.0F;
		player.rotationYawHead = player.rotationYaw;
		player.prevRotationYawHead = player.rotationYaw;
		GlStateManager.translate(0.0F, 0.0F, 0.0F);

		rm.setPlayerViewY(180.0F);
		rm.setRenderShadow(false);
		rm.doRenderEntity(player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, true);
		rm.setRenderShadow(true);
		player.renderYawOffset = f2;
		player.rotationYaw = f3;
		player.rotationPitch = f4;
		player.prevRotationYawHead = f6;
		player.rotationYawHead = f5;
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	/**
	 * Draws a player that is rotatable
	 *
	 * @param minecraft the minecraft instance
	 * @param par1      xPos
	 * @param par2      yPos
	 * @param par3      scale
	 * @param par4
	 * @param par5
	 * @param par6      rotation
	 * @param player    player to be rendered
	 */
	@SuppressWarnings("static-access")
	public static void drawPlayerRotatable(Minecraft minecraft, int par1, int par2, int par3, float par4, float par5,
			float par6, EntityPlayer player)
	{
		player.getActiveItemStack().setCount(0);

		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GL11.glTranslatef(par1, par2, 50.0f);
		GL11.glScalef(-par3, par3, par3);
		GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
		float f2 = player.renderYawOffset;
		float f3 = player.rotationYaw;
		float f4 = player.rotationPitch;
		float f5 = player.rotationYawHead;
		par4 -= 19;
		GL11.glRotatef(135.0f, 0.0f, 1.0f, 0.0f);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0f, 0.0f, 1.0f, 0.0f);
		player.renderYawOffset = par6;
		player.rotationYaw = (float) Math.atan(par4 / 40.0f) * 40.0f;
		player.rotationYaw = par6;
		player.rotationYawHead = player.rotationYaw;
		player.rotationPitch = (float) Math.sin(minecraft.getSystemTime() / 500.0f) * 3.0f;
		GL11.glTranslatef(0.0f, (float) player.getYOffset(), 0.0f);
		minecraft.getRenderManager().playerViewY = 180f;
		minecraft.getRenderManager().doRenderEntity(player, 0.0d, 0.0d, 0.0d, 0.0f, 1.0f, true);
		player.renderYawOffset = f2;
		player.rotationYaw = f3;
		player.rotationPitch = f4;
		player.rotationYawHead = f5;
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	private static int checkRotation(int mouseX, float rotation)
	{
		return rotation >= 100 ? -mouseX : rotation <= -100 ? -mouseX : mouseX;
	}

}
