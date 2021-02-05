package me.azlaaz.bazooka;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;


public class Events implements Listener {

    private List<Entity> getEntitys(Player player){
        List<Entity> entitys = new ArrayList<Entity>();
        for(Entity e : player.getNearbyEntities(201, 40, 201)){
            if(e instanceof LivingEntity){
                if(getLookAt(player, (LivingEntity) e)){
                    entitys.add(e);
                }
            }
        }
        return entitys;
    }

    private boolean getLookAt(Player player, LivingEntity livingEntity) {
        Location eye = player.getEyeLocation();
        Vector toEntity = livingEntity.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());
        return dot > 0.99D;
    }

    @EventHandler
    public void getJinxed(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission("minecraft.command.op")) {
            Player player = event.getPlayer();
            ItemStack holystick = Events.holystick();
            if (event.getItem().equals(holystick)) {
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    for (Entity e : getEntitys(player)) {
                        if (e instanceof Villager) {
                            Location a = e.getLocation();
                            Location b = player.getEyeLocation();
                            double distance = a.distance(b);
                            if (distance <= 60) {
                                Bukkit.broadcastMessage("You see this entity " + e + " The distance between you: " + distance);
                                LivingEntity ent1 = (LivingEntity) e;
                                missile(player, ent1);

                            }
                        }
                    }
                }
            }
        }
    }

    private static Projectile missile(Player player, LivingEntity livingEntity) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bazooka.getPlugin(Bazooka.class), new Runnable() {
            @Override
            public void run() {
        Location a = player.getLocation().add(player.getLocation().getDirection().normalize().multiply(3)).add(0, 2, 0);;
        Vector abo = livingEntity.getLocation().getBlock().getLocation().toVector().toBlockVector().normalize();
        Entity missile = player.getWorld().spawnEntity(a,EntityType.ARROW);
        missile.setVelocity(abo);
            }
        },0,1);
        return null;
    }

    static ItemStack holystick() {
        ItemStack holystick = new ItemStack(Material.STICK,1);
        ItemMeta stickMeta = holystick.getItemMeta();
        String displayName = ChatColor.DARK_RED + "" + ChatColor.BOLD + "Roketatar";
        stickMeta.setDisplayName(displayName);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.AQUA + "AzlaazTR " + ChatColor.GOLD + "" + "tarafından tasarlandı.");
        lore.add(ChatColor.GOLD + "Seçilen varlığın kafasına roketler yollar.");
        stickMeta.setLore(lore);
        holystick.setItemMeta(stickMeta);
        return holystick;
    }
}
