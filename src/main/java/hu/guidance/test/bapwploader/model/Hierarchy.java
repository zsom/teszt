package hu.guidance.test.bapwploader.model;

public class Hierarchy {
	private int id;
	private int from;
	private int to;
	private int hierarchy;
	private int count = 1;
	
	public Hierarchy() {}
	
	public Hierarchy(int from, int to, int hierarchy) {
		this.from = from;
		this.to = to;
		this.hierarchy = hierarchy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hierarchy [id=");
		builder.append(id);
		builder.append(", from=");
		builder.append(from);
		builder.append(", to=");
		builder.append(to);
		builder.append(", hierarchy=");
		builder.append(hierarchy);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}
	
	
}
