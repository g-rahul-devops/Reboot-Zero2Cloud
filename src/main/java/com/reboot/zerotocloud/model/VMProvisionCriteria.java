package com.reboot.zerotocloud.model;

public class VMProvisionCriteria {
	
	private String project;
	
	private String zone;
	
	private String name;
	
	private String tags;
	
	private String machineType;
	
	private String disks;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getDisks() {
		return disks;
	}

	public void setDisks(String disks) {
		this.disks = disks;
	}
	

}
