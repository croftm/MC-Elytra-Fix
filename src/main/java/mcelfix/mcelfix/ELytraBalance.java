package mcelfix.mcelfix;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import static org.bukkit.Bukkit.getServer;

public class ELytraBalance implements Listener {
    private Server server;
    public ELytraBalance()
    {
        server = getServer();
    }
    final String WELCOME_MSG = "Mending is currently disabled on elytras";
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        server.dispatchCommand(server.getConsoleSender(),"msg "+ player.getDisplayName() + " " + WELCOME_MSG);
    }
    @EventHandler
    public void onElytraGlide(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        EntityEquipment equipment = player.getEquipment();
        ItemStack chestplate = equipment.getChestplate();
        Inventory inventory = player.getInventory();
        if (chestplate != null)
        {
            if (chestplate.containsEnchantment(Enchantment.MENDING))
            {
                if(chestplate.getData().getItemType().equals(Material.LEGACY_ELYTRA))
                {
                    System.out.println("REMOVED MENDING ELYTRA");
                    chestplate.removeEnchantment(Enchantment.MENDING);
                    server.dispatchCommand(server.getConsoleSender(),"msg "+ player.getDisplayName() + " Removed mending from elytra no refund");
                }
            }
        }
        if (inventory.contains(Material.LEGACY_ELYTRA) || inventory.contains(Material.ELYTRA))
        {
            ItemStack[] inventoryContents = inventory.getContents();
            for(int i=0;i<inventoryContents.length;i++)
            {
                if (inventoryContents[i]!=null)
                {
                    Material itemType = inventoryContents[i].getData().getItemType();
                    if (itemType.equals(Material.LEGACY_ELYTRA) || itemType.equals(Material.ELYTRA))
                    {
                        if(inventoryContents[i].containsEnchantment(Enchantment.MENDING))
                        {
                            System.out.println("REMOVED MENDING ELYTRA");
                            inventoryContents[i].removeEnchantment(Enchantment.MENDING);
                            server.dispatchCommand(server.getConsoleSender(),"msg "+ player.getDisplayName() + " Removed mending from elytra no refund");
                        }
                    }
                }
            }
        }
    }
}
