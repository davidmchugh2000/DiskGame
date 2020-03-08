package edu.ycp.cs201.disks;

public enum DiskColor {
	BLUE("0000AA"),
	GREEN("00AA00"),
	CYAN("00AAAA"),
	RED("AA0000"),
	MAGENTA("AA00AA"),
	BROWN("AA5500"),
	LIGHT_GRAY("AAAAAA"),
	GRAY("555555"),
	LIGHT_BLUE("5555FF"),
	LIGHT_GREEN("55FF55"),
	LIGHT_CYAN("55FFFF"),
	LIGHT_RED("FF5555"),
	LIGHT_MAGENTA("FF55FF"),
	YELLOW("FFFF55"),
	WHITE("FFFFFF");
	
	private String rgb;
	
	private DiskColor(String rgb) {
		this.rgb = rgb;
	}
	
	public int red() {
		return hexToInt(rgb.substring(0, 2));
	}
	
	public int green() {
		return hexToInt(rgb.substring(2, 4));
	}
	
	public int blue() {
		return hexToInt(rgb.substring(4, 6));
	}
	
	private int hexToInt(String s) {
		return Integer.parseInt(s, 16);
	}
}
