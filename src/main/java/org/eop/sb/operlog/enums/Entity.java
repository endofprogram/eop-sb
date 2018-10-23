package org.eop.sb.operlog.enums;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
public enum Entity {

	Role("", "");
	
	private String code;
	private String name;
	
	private Entity(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static final Entity parse(String code) {
		for (Entity entity : values()) {
			if (entity.code.equals(code)) {
				return entity;
			}
		}
		throw new IllegalArgumentException("entity code '" + code + "' is invalid.");
	}
}
