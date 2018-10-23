package org.eop.sb.operlog.enums;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
public enum Operate {

	Add("", "");
	
	private String code;
	private String name;
	
	private Operate(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static final Operate parse(String code) {
		for (Operate operate : values()) {
			if (operate.code.equals(code)) {
				return operate;
			}
		}
		throw new IllegalArgumentException("operate code '" + code + "' is invalid.");
	}
}
