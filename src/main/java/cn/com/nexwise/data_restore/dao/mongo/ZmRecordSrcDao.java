package cn.com.nexwise.data_restore.dao.mongo;

import cn.com.nexwise.data_restore.dao.mongo.entites.ZmRecord;
import org.mongodb.morphia.query.FindOptions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZmRecordSrcDao extends SourceMDao<ZmRecord> {
    @Override
    public Class<ZmRecord> getEntityClass() {
        return ZmRecord.class;
    }

    public List<ZmRecord> getPageList(int pageSize, int skip) {
        List<ZmRecord> records = null;

        FindOptions findOptions = new FindOptions();
        findOptions.limit(pageSize).skip(skip);

//        records = super.getDs().find(this.getEntityClass()).order("-catchtime").asList(findOptions);
        records = super.getDs().find(this.getEntityClass()).asList(findOptions);
        return records;
    }

}
