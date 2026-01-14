package co.id.gradyfernando.model;

import java.util.List;

public class BaseDaftarSurat<T> {
    private List<T> data;
    private List<AdvanceFilter> filters;
    
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public List<AdvanceFilter> getFilters() {
        return filters;
    }
    public void setFilters(List<AdvanceFilter> filters) {
        this.filters = filters;
    }

    
}
