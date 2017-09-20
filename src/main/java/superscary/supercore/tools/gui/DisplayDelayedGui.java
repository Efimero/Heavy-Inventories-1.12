package superscary.supercore.tools.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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
public class DisplayDelayedGui
{

	private int delayTicks;
	private Minecraft mcClient;
	private GuiScreen screen;

	public DisplayDelayedGui(int par1Int, GuiScreen par2GuiScreen)
	{
		this.delayTicks = par1Int;
		this.mcClient = FMLClientHandler.instance().getClient();
		this.screen = par2GuiScreen;
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		if (event.phase.equals(TickEvent.Phase.START)) { return; }

		if (--delayTicks <= 0)
		{
			mcClient.displayGuiScreen(screen);
			MinecraftForge.EVENT_BUS.unregister(this);
		}
	}

}
