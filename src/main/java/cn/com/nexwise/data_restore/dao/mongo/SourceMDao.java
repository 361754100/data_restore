package cn.com.nexwise.data_restore.dao.mongo;

import cn.com.nexwise.data_restore.config.SourceMConfig;
import com.mongodb.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Mongodb 数据库操作基类
 *
 * @param <T>
 */
public abstract class SourceMDao<T> {

//    @Autowired
    private Datastore ds;

    @Autowired
    private SourceMConfig mongoConfig;

    protected BasicDAO<T, String> dao;

    public Datastore initDs() {
        Morphia morphia = new Morphia();

        MongoCredential credential = MongoCredential.createCredential(mongoConfig.getUsername(),
                mongoConfig.getDatabase(), mongoConfig.getPassword().toCharArray());   //验证对象
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();     //连接操作对象
        MongoClient mongoClient = new MongoClient(new ServerAddress(mongoConfig.getHost(),mongoConfig.getPort()), credential, options);   //连接对象

        return morphia.createDatastore(mongoClient, mongoConfig.getDatabase());
    }


    @PostConstruct
    public void init() {
        this.ds = initDs();
        this.dao = new BasicDAO<T, String>(this.getEntityClass(), this.ds);
        this.dao.ensureIndexes();
    }

    public void save(T entity) {
        this.dao.getDatastore().save(entity);
    }

    public void saveList(List<T> entities) {
        this.dao.getDatastore().save(entities);
    }

    public void removeAll() {
        this.dao.getDatastore().delete(ds.createQuery(this.getEntityClass()));
    }

    public void removeByField(String field, Object value){
        this.dao.getDatastore().delete(ds.createQuery(this.getEntityClass()).filter(field, value));
    }

    public WriteResult removeByQuery(Query<T> query){
        return this.dao.getDatastore().delete(query);
    }

    public List<T> listByField(String field, Object value){
        return this.getDs().createQuery(this.getEntityClass()).field(field).equal(value).asList();
    }

    public T get(String objectId) {
        return this.getDs().get(this.getEntityClass(), objectId);
    }

    public List<T> listAll(){
        return this.getDs().find(this.getEntityClass()).asList();
    }

    public UpdateResults update(Query<T> query, UpdateOperations<T> operations){
        return this.getDs().update(query, operations);
    }

    public void updateField2All(String field, Object value){
        this.getDs().update(ds.find(this.getEntityClass()), ds.createUpdateOperations(getEntityClass()).set(field, value));
    }

    /**
     * Converts from a List<Key> to their id values
     *
     * @param keys
     * @return
     */
    protected List<?> keysToIds(List<Key<T>> keys) {
        ArrayList ids = new ArrayList(keys.size() * 2);
        for (Key<T> key : keys)
            ids.add(key.getId());
        return ids;
    }

    /** The underlying collection for this DAO */
    public DBCollection getCollection() {
        return ds.getCollection(getEntityClass());
    }


    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#createQuery()
     */
    public Query<T> createQuery() {
        return ds.createQuery(getEntityClass());
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#createUpdateOperations()
     */
    public UpdateOperations<T> createUpdateOperations() {
        return ds.createUpdateOperations(getEntityClass());
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#findIds(java.lang.String, java.lang.Object)
     */
    public List<T> findIds(String key, Object value) {
        return (List<T>) keysToIds(ds.find(getEntityClass(), key, value).asKeyList());
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#findIds()
     */
    public List<T> findIds() {
        return (List<T>) keysToIds(ds.find(getEntityClass()).asKeyList());
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#findIds(com.google.code.morphia.query.Query)
     */
    public List<T> findIds(Query<T> q) {
        return (List<T>) keysToIds(q.asKeyList());
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#exists(com.google.code.morphia.query.Query)
     */
    public boolean exists(Query<T> q) {
        return ds.getCount(q) > 0;
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#count()
     */
    public long count() {
        return ds.getCount(getEntityClass());
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#count(java.lang.String, java.lang.Object)
     */
    public long count(String key, Object value) {
        return count(ds.find(getEntityClass(), key, value));
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#count(com.google.code.morphia.query.Query)
     */
    public long count(Query<T> q) {
        return ds.getCount(q);
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#findOne(com.google.code.morphia.query.Query)
     */
    public T findOne(Query<T> q) {
        return q.get();
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#find()
     */
    public QueryResults<T> find() {
        return createQuery();
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#find(com.google.code.morphia.query.Query)
     */
    public List<T> find(Query<T> q) {
        return q.asList();
    }

    /* (non-Javadoc)
     * @see com.google.code.morphia.DAO#getDatastore()
     */
    public Datastore getDatastore() {
        return ds;
    }

    public void ensureIndexes() {
        ds.ensureIndexes(getEntityClass());
    }

    public abstract Class<T> getEntityClass();

    protected Datastore getDs() {
        return this.dao.getDatastore();
    }

}
