package org.eop.sb.operlog.enums;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
public enum Module {

	AccessControl("", "");
	
	private String code;
	private String name;
	
	private Module(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static final Module parse(String code) {
		for (Module module : values()) {
			if (module.code.equals(code)) {
				return module;
			}
		}
		throw new IllegalArgumentException("module code '" + code + "' is invalid.");
	}
}
