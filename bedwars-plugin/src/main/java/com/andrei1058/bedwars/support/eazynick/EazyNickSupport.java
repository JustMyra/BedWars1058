package com.andrei1058.bedwars.support.eazynick;

import com.justixdev.eazynick.EazyNick;
import com.justixdev.eazynick.api.NickedPlayerData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public class EazyNickSupport
{
	private static Support supportEazyNick = new WithoutSupport();
	
	public interface Support
	{
		String getName(final @NotNull Player player);
		
		String getDisplayName(final @NotNull Player player);
	}
	
	public static class WithoutSupport implements Support
	{
		@Override
		public String getName(final @NotNull Player player)
		{
			return player.getName();
		}
		
		@Override
		public String getDisplayName(final @NotNull Player player)
		{
			return player.getDisplayName();
		}
	}
	
	public static class WithSupport implements Support
	{
		//private final Utils eazyNickUtils = EazyNick.getInstance().getUtils();
		private final Map<UUID, NickedPlayerData> nickedPlayers = EazyNick.getInstance().getUtils().getNickedPlayers();
		
		@Override
		public String getName(final @NotNull Player player)
		{
			// final Map<UUID, NickedPlayerData> nickedPlayers = eazyNickUtils.getNickedPlayers();
			if (nickedPlayers.containsKey(player.getUniqueId()))
			{
				return nickedPlayers.get(player.getUniqueId()).getNickName();
			}
			else
			{
				return player.getName();
			}
		}
		
		@Override
		public String getDisplayName(final @NotNull Player player)
		{
			if (nickedPlayers.containsKey(player.getUniqueId()))
			{
				return nickedPlayers.get(player.getUniqueId()).getNickName(); // TODO check if this is right -- should i get the eazynick nickname?
			}
			else
			{
				return player.getDisplayName();
			}
		}
	}
	
	public static String getName(final @NotNull Player player)
	{
		return supportEazyNick.getName(player);
	}
	
	public static String getDisplayName(final @NotNull Player player)
	{
		return supportEazyNick.getDisplayName(player);
	}
	
	public static void setSupport(final Support support)
	{
		supportEazyNick = support;
	}
	
	/* EazyNick Support -- START
	//.replace("{playername}", p.getName())
     .replace("{playername}", EazyNickSupport.getName(p))
     //.replace("{player}", p.getDisplayName())
     .replace("{player}", EazyNickSupport.getDisplayName(p))
     /* EazyNick Support -- END
	 */
}
