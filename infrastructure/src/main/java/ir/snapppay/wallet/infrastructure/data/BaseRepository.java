package ir.snapppay.wallet.infrastructure.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@NoRepositoryBean
public interface BaseRepository<ENTITY extends AbstractEntity, ID extends Serializable> extends JpaRepository<ENTITY, ID>, JpaSpecificationExecutor<ENTITY> {


    @Modifying
    @Query(value = "update #{#entityName} o set o.deleted = true where o.id = :id")
    void softDeleteById(final @Param("id") ID id);


    @Modifying
    @Query(value = "delete from #{#entityName} o where o.id = :id")
    void deleteById(final @Param("id") ID id);

}
