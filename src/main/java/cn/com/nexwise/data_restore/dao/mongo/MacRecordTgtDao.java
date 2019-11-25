package cn.com.nexwise.data_restore.dao.mongo;

import cn.com.nexwise.data_restore.dao.mongo.entites.MacRecord;
import cn.com.nexwise.data_restore.dao.mongo.entites.ZmRecord;
import org.springframework.stereotype.Component;

@Component
public class MacRecordTgtDao extends TargetMDao<MacRecord> {
    @Override
    public Class<MacRecord> getEntityClass() {
        return MacRecord.class;
    }

    
}
