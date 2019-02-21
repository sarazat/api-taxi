package com.gdm.vehicleapi.util;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

public class PaginationUtil {

  private PaginationUtil() { }

  public static HttpHeaders generatePaginationHttpHeaders(final Pageable pageable) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("offset", "" + pageable.getOffset());
    headers.add("pageSize", "" + pageable.getPageSize());
    headers.add("pageNumber", "" + pageable.getPageNumber());
    return headers;
  }
}
