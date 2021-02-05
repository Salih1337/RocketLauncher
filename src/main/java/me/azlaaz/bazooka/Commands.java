package me.azlaaz.bazooka;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equals("roketatar")) {
            if (sender instanceof Player) {
                ItemStack holystick = Events.holystick();
                Inventory inventory = ((Player)sender).getInventory();
                inventory.addItem(holystick);

            }
        }

        return false;
    }
}
