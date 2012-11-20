package me.ialihd.weirdploogin2.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ialihd.weirdploogin2.WeirdPloogin;

public class Config {
	public static WeirdPloogin plugin = WeirdPloogin.plugin;
	public final ConfigHandler config = new ConfigHandler("plugins/WeirdPloogin/config.yml", "WeirdPloogin config");

	public void addConfigDefaults() {
		config.addDefault("time.day", 1000);
		config.addDefault("time.night", 13000);
		config.addDefault("message.ban", "You've been banned ");
		config.addDefault("message.kick", "You've been kicked ");
	}

	public static boolean load() {
		try {
			File configFile = new File(plugin.getDataFolder() + "/config.yml");
			if ((!configFile.exists()) && (!getFile("config.yml"))) {
				return false;
			}
			YamlConfiguration config = new YamlConfiguration();
			config.load(configFile);

		} catch (FileNotFoundException ex) {
			plugin.log.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			plugin.log.log(Level.SEVERE, null, ex);
		} catch (InvalidConfigurationException ex) {
            plugin.log.log(Level.SEVERE, null, ex);
		}
		return false;
	}
    private static boolean getFile(String filename) {
        try {
            if (!plugin.getDataFolder().exists())
                plugin.getDataFolder().mkdirs();
            File file = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + filename);
            file.createNewFile();

            InputStream fis = plugin.getResource("files/" + filename);
            FileOutputStream fos = new FileOutputStream(file);
            try {
                byte[] buf = new byte[1024];
                int i = 0;
                while ((i = fis.read(buf)) != -1)
                    fos.write(buf, 0, i);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            } finally {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }
            return true;
        } catch (IOException ex) {
            plugin.log.log(Level.SEVERE, String.format("PluginList: Error retrieving %1$s", new Object[]{filename}));
        }
        return false;
    }
    
    public void initialize(){
    	this.config.load();
    	addDefaults();
    	this.config.copyDefaults(true);
    	this.config.save();
    }

	private void addDefaults() {
		config.addDefault("time.day", 1000);
		config.addDefault("time.night", 13000);
		config.addDefault("message.ban", "You've been banned");
		config.addDefault("message.kick", "You've been kicked");
	}
	
}
