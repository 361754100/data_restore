package cn.com.nexwise.data_restore.dao.mongo;

import cn.com.nexwise.data_restore.dao.mongo.entites.ZmRecord;
import org.springframework.stereotype.Component;

@Component
public class ZmRecordTgtDao extends TargetMDao<ZmRecord> {
    @Override
    public Class<ZmRecord> getEntityClass() {
        return ZmRecord.class;
    }

    
}
