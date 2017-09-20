package superscary.supercore.tools;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

import static superscary.supercore.resources.ModResources.MODID;

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
public class Utilities
{

	/**
	 * Get capability handler for an {@link ICapabilityProvider}
	 *
	 * @param provider   the provider
	 * @param capability the capability
	 * @param facing     facing direction
	 * @param <T>        handler type
	 * @return the handler
	 */
	@Nullable
	public static <T> T getCapability(@Nullable ICapabilityProvider provider, Capability<T> capability,
			@Nullable EnumFacing facing)
	{
		return provider != null && provider.hasCapability(capability, facing) ?
				provider.getCapability(capability, facing) :
				null;
	}

	public static ResourceLocation getResource(ResourceType type, String name)
	{
		return new ResourceLocation(MODID + ":textures/" + type.getPrefix() + name);
	}

	public static ResourceLocation getBlocksTexture()
	{
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	public static void color(EnumColor color)
	{
		color(color, 1.0F);
	}

	public static void color(EnumColor color, float alpha)
	{
		color(color, alpha, 1.0F);
	}

	public static void color(EnumColor color, float alpha, float multiplier)
	{
		GL11.glColor4f(color.getColor(0) * multiplier, color.getColor(1) * multiplier, color.getColor(2) * multiplier,
				alpha);
	}

	public static void resetColor()
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void color(int color)
	{
		float cR = (color >> 16 & 0xFF) / 255.0F;
		float cG = (color >> 8 & 0xFF) / 255.0F;
		float cB = (color & 0xFF) / 255.0F;

		GL11.glColor3f(cR, cG, cB);
	}

	public static final <T extends TileEntity> T getTileEntityAt(IBlockAccess access, Class<T> entityType,
			BlockPos coords)
	{
		if (access != null && entityType != null && coords != null)
		{
			TileEntity entity = access.getTileEntity(coords);

			if (entity != null && entity.getClass() == entityType)
			{
				return (T) entity;
			}
		}

		return null;
	}

	public static enum ResourceType
	{
		GUI("gui"),
		GUI_ELEMENT("gui/elements"),
		SOUND("sound"),
		RENDER("render"),
		TEXTURE_BLOCKS("textures/blocks"),
		TEXTURE_ITEMS("textures/items"),
		MODEL("models"),
		INFUSE("infuse");

		private String prefix;

		private ResourceType(String s)
		{
			prefix = s;
		}

		public String getPrefix()
		{
			return prefix + "/";
		}
	}

}
