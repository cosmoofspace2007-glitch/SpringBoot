package Baiss4.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T>
{
    List<T> items;
    int page;
    int size;
    int totalItems;
    int totalPages;
    boolean isLast;
}
