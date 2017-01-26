package dataFilters;

import java.util.List;
import projectFile.FieldData;

public interface FieldDataFilter {
	List<FieldData> filter(List<FieldData> data);
}
