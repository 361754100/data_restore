package cn.com.nexwise.data_restore.dao.mongo;

import cn.com.nexwise.data_restore.dao.mongo.entites.MacRecord;
import org.mongodb.morphia.query.FindOptions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MacRecordSrcDao extends SourceMDao<MacRecord> {
    @Override
    public Class<MacRecord> getEntityClass() {
        return MacRecord.class;
    }

    public List<MacRecord> getPageList(int pageSize, int skip) {
        List<MacRecord> records = null;

        FindOptions findOptions = new FindOptions();
        findOptions.limit(pageSize).skip(skip);

//        records = super.getDs().find(this.getEntityClass()).order("-creationTime").asList(findOptions);
        records = super.getDs().find(this.getEntityClass()).asList(findOptions);
        return records;
    }

}
