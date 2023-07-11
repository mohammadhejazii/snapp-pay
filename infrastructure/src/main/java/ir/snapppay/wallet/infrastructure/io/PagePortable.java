package ir.snapppay.wallet.infrastructure.io;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Getter
@Setter
@ToString
public final class PagePortable<T extends Serializable> implements Serializable {


    private static final long serialVersionUID = -8418979544372831588L;
    private long number;
    private long numberOfElements;
    private long size;
    private long totalElements;
    private long totalPages;
    private List<T> content;


    private PagePortable() {

    }

    private <T> PagePortable(Class<T> type) {
        this.content = new ArrayList<>();
    }

    private PagePortable(final Page<T> page) {
        this.content = page.getContent() != null ? page.getContent() : new ArrayList<>();
        this.number = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    private PagePortable(final List<T> content, final Page<T> page) {
        this.content = content != null ? content : new ArrayList<>();
        this.number = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }


    private PagePortable(final Pageable pageable) {
        this.content = new ArrayList<>();
        this.number = pageable.getPageNumber();
        this.numberOfElements = 0;
        this.size = pageable.getPageSize();
        this.totalElements = 0;
        this.totalPages = 0;
    }

    public static <T extends Serializable> PagePortable<T> of(final Page<T> page) {
        return new PagePortable(page);
    }

    public static <T extends Serializable> PagePortable<T> empty() {
        return new PagePortable();
    }

    public static <T extends Serializable> PagePortable<T> of(final Pageable pageable) {
        return new PagePortable(pageable);
    }

    public static <T extends Serializable> PagePortable<T> of(final List<T> content, final Page page) {
        return new PagePortable(content, page);
    }

    public List<T> getContent() {
        if (content == null) {
            return new ArrayList<>();
        }
        return content;
    }


}
