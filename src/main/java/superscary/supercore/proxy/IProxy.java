package superscary.supercore.proxy;

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
public interface IProxy
{

	/**
	 * To register this as your server proxy:
	 * @SidedProxy(serverSide = "path.to.your.server.proxy", clientSide = "path.to.your.client.proxy")
	 * public static IProxy proxy;
	 *
	 * Call each of the methods in their respective classes defined below.
	 *
	 * Make sure your Server Proxy and Client Proxy are implementing this interface.
	 */

	/**
	 * Used to register your Minecraft Forge pre-init method ({@link net.minecraftforge.fml.common.event.FMLPreInitializationEvent})
	 */
	void preInit();

	/**
	 * Used to register your Minecraft Forge init method ({@link net.minecraftforge.fml.common.event.FMLInitializationEvent})
	 */
	void init();

	/**
	 * Used to register you Minecraft Forge post init method ({@link net.minecraftforge.fml.common.event.FMLPostInitializationEvent})
	 */
	void postInit();

}
