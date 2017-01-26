package dataFilters;

import java.util.List;

import projectFile.MethodData;

public class SyntheticFilter implements MethodDataFilter {

	@Override
	public List<MethodData> filter(List<MethodData> datas) {
		datas.removeIf((MethodData data) -> {
			return data.getMethodName().contains("lambda$");
		});
		
		return datas;
	}

}
