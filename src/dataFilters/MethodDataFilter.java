package dataFilters;

import java.util.List;

import projectFile.MethodData;

public interface MethodDataFilter {
	List<MethodData> filter(List<MethodData> datas);
}
