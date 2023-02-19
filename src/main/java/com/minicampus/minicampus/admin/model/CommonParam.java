package com.minicampus.minicampus.admin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonParam {
    Long pageIndex;
    Long pageSize;

    String searchType;
    String searchValue;

    /**
     * limit 0, 10 --> pageIndex : 1
     * limit 10, 10 --> pageIndex : 2
     * limit 20, 10 --> pageIndex : 3
     * limit 30, 10 --> pageIndex : 4
     */

    public Long getPageStart() {
        init();
        return (pageIndex - 1) * pageSize;
    }

    public Long getPageEnd() {
        init();
        return pageSize;
    }

    public void init() {
        if(pageIndex == null || pageIndex < 1) {
            pageIndex = 1L;
        }

        if(pageSize == null || pageSize < 1) {
            pageSize = 10L;
        }

    }

    public String getQueryString() {
        init();
        StringBuilder sb = new StringBuilder();
        if(searchType != null && searchType.length() > 0){
            sb.append(String.format("searchType=%s", searchType));
        }

        if(searchValue != null && searchValue.length() > 0){
            if(sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("searchValue=%s", searchValue));
        }
        return sb.toString();
    }
}
