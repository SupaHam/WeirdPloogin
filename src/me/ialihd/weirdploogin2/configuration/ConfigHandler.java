package me.ialihd.weirdploogin2.configuration;

import java.io.File;
import java.io.IOException;

import me.ialihd.weirdploogin2.WeirdPloogin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler {

	public static WeirdPloogin plugin = WeirdPloogin.plugin;
	private FileConfiguration config;
	private File file;

	public ConfigHandler(String fileName, String header) {
		this.file = new File(fileName);
		this.config = new YamlConfiguration();
		this.config.options().header(header);
		if (!(this.file.exists()))
			create();
		else
			load();
	}

	public boolean pathExists(String path) {
		return (this.config.get(path) != null);
	}

	public String getString(String path) {
		if (pathExists(path)) {
			return this.config.get(path).toString();
		}
		return "";
	}

	public double getDouble(String path) {
		if (pathExists(path)) {
			if (this.config.getString(path) == null) {
				if (this.config.get(path) instanceof Integer) {
					return this.config.getInt(path);
				}
				return this.config.getDouble(path);
			}
			return Double.parseDouble(this.config.getString(path));
		}
		return 0.0D;
	}

	public void setDouble(String path, double value) {
		this.config.set(path, String.valueOf(value));
	}

	public void setString(String path, String value) {
		this.config.set(path, value);
	}

	public void load() {
		try {
			this.config.load(this.file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void create() {
		try {
			plugin.log.info("[ Creating new file at " + this.file.getName() + "! ]");
			this.file.getParentFile().mkdirs();
			this.file.createNewFile();
			save();
		} catch (IOException e) {
			plugin.getLogger().severe("[ Unable to create " + this.file.getPath() + "! ]");
		}
	}

	public void copyDefaults(boolean value) {
		this.config.options().copyDefaults(value);
	}

	public void addDefault(String path, int value) {
		this.config.addDefault(path, Integer.valueOf(value));
	}

	public void addDefault(String path, String value) {
		this.config.addDefault(path, value);
	}
}
