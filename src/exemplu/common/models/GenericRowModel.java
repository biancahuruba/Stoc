package exemplu.common.models;

public abstract class GenericRowModel {
	public abstract Attribute getAttribute(final int index);

	public abstract void setAttribute(int index, String value);
}
