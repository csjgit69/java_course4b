package course4b;

import java.util.ArrayList;

public class FiltersAll implements Filter {
    ArrayList<Filter> filters;
    
    public FiltersAll() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }       
        return true;
    }

}
