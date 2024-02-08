package com.example.foodrecipeapp.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.foodrecipeapp.dao.RecipesDao;
import com.example.foodrecipeapp.dao.RecipesDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RecipesDatabase_Impl extends RecipesDatabase {
  private volatile RecipesDao _recipesDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `RECIPES_TABLE` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `dishName` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Category` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `categoryItems` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `CategoryItems` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `idcategory` TEXT, `strcategory` TEXT, `strcategorythumb` TEXT, `strcategorydescription` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `MEAL_TABLE` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `meals` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `MEAL_ITEMS_TABLE` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `strMeal` TEXT, `strMealThumb` TEXT, `idMeal` TEXT, `categoryName` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c656e627c0bf733f2e0bbe0245ba42d1')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `RECIPES_TABLE`");
        db.execSQL("DROP TABLE IF EXISTS `Category`");
        db.execSQL("DROP TABLE IF EXISTS `CategoryItems`");
        db.execSQL("DROP TABLE IF EXISTS `MEAL_TABLE`");
        db.execSQL("DROP TABLE IF EXISTS `MEAL_ITEMS_TABLE`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRECIPESTABLE = new HashMap<String, TableInfo.Column>(2);
        _columnsRECIPESTABLE.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRECIPESTABLE.put("dishName", new TableInfo.Column("dishName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRECIPESTABLE = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRECIPESTABLE = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRECIPESTABLE = new TableInfo("RECIPES_TABLE", _columnsRECIPESTABLE, _foreignKeysRECIPESTABLE, _indicesRECIPESTABLE);
        final TableInfo _existingRECIPESTABLE = TableInfo.read(db, "RECIPES_TABLE");
        if (!_infoRECIPESTABLE.equals(_existingRECIPESTABLE)) {
          return new RoomOpenHelper.ValidationResult(false, "RECIPES_TABLE(com.example.foodrecipeapp.entity.Recipes).\n"
                  + " Expected:\n" + _infoRECIPESTABLE + "\n"
                  + " Found:\n" + _existingRECIPESTABLE);
        }
        final HashMap<String, TableInfo.Column> _columnsCategory = new HashMap<String, TableInfo.Column>(2);
        _columnsCategory.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategory.put("categoryItems", new TableInfo.Column("categoryItems", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategory = new TableInfo("Category", _columnsCategory, _foreignKeysCategory, _indicesCategory);
        final TableInfo _existingCategory = TableInfo.read(db, "Category");
        if (!_infoCategory.equals(_existingCategory)) {
          return new RoomOpenHelper.ValidationResult(false, "Category(com.example.foodrecipeapp.entity.Category).\n"
                  + " Expected:\n" + _infoCategory + "\n"
                  + " Found:\n" + _existingCategory);
        }
        final HashMap<String, TableInfo.Column> _columnsCategoryItems = new HashMap<String, TableInfo.Column>(5);
        _columnsCategoryItems.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoryItems.put("idcategory", new TableInfo.Column("idcategory", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoryItems.put("strcategory", new TableInfo.Column("strcategory", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoryItems.put("strcategorythumb", new TableInfo.Column("strcategorythumb", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoryItems.put("strcategorydescription", new TableInfo.Column("strcategorydescription", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategoryItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategoryItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategoryItems = new TableInfo("CategoryItems", _columnsCategoryItems, _foreignKeysCategoryItems, _indicesCategoryItems);
        final TableInfo _existingCategoryItems = TableInfo.read(db, "CategoryItems");
        if (!_infoCategoryItems.equals(_existingCategoryItems)) {
          return new RoomOpenHelper.ValidationResult(false, "CategoryItems(com.example.foodrecipeapp.entity.CategoryItems).\n"
                  + " Expected:\n" + _infoCategoryItems + "\n"
                  + " Found:\n" + _existingCategoryItems);
        }
        final HashMap<String, TableInfo.Column> _columnsMEALTABLE = new HashMap<String, TableInfo.Column>(2);
        _columnsMEALTABLE.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMEALTABLE.put("meals", new TableInfo.Column("meals", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMEALTABLE = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMEALTABLE = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMEALTABLE = new TableInfo("MEAL_TABLE", _columnsMEALTABLE, _foreignKeysMEALTABLE, _indicesMEALTABLE);
        final TableInfo _existingMEALTABLE = TableInfo.read(db, "MEAL_TABLE");
        if (!_infoMEALTABLE.equals(_existingMEALTABLE)) {
          return new RoomOpenHelper.ValidationResult(false, "MEAL_TABLE(com.example.foodrecipeapp.entity.Meal).\n"
                  + " Expected:\n" + _infoMEALTABLE + "\n"
                  + " Found:\n" + _existingMEALTABLE);
        }
        final HashMap<String, TableInfo.Column> _columnsMEALITEMSTABLE = new HashMap<String, TableInfo.Column>(5);
        _columnsMEALITEMSTABLE.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMEALITEMSTABLE.put("strMeal", new TableInfo.Column("strMeal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMEALITEMSTABLE.put("strMealThumb", new TableInfo.Column("strMealThumb", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMEALITEMSTABLE.put("idMeal", new TableInfo.Column("idMeal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMEALITEMSTABLE.put("categoryName", new TableInfo.Column("categoryName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMEALITEMSTABLE = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMEALITEMSTABLE = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMEALITEMSTABLE = new TableInfo("MEAL_ITEMS_TABLE", _columnsMEALITEMSTABLE, _foreignKeysMEALITEMSTABLE, _indicesMEALITEMSTABLE);
        final TableInfo _existingMEALITEMSTABLE = TableInfo.read(db, "MEAL_ITEMS_TABLE");
        if (!_infoMEALITEMSTABLE.equals(_existingMEALITEMSTABLE)) {
          return new RoomOpenHelper.ValidationResult(false, "MEAL_ITEMS_TABLE(com.example.foodrecipeapp.entity.MealsItem).\n"
                  + " Expected:\n" + _infoMEALITEMSTABLE + "\n"
                  + " Found:\n" + _existingMEALITEMSTABLE);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c656e627c0bf733f2e0bbe0245ba42d1", "bed7cdccc5428ae4ee2abb57d7819df1");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "RECIPES_TABLE","Category","CategoryItems","MEAL_TABLE","MEAL_ITEMS_TABLE");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `RECIPES_TABLE`");
      _db.execSQL("DELETE FROM `Category`");
      _db.execSQL("DELETE FROM `CategoryItems`");
      _db.execSQL("DELETE FROM `MEAL_TABLE`");
      _db.execSQL("DELETE FROM `MEAL_ITEMS_TABLE`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RecipesDao.class, RecipesDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RecipesDao getRecipesDao() {
    if (_recipesDao != null) {
      return _recipesDao;
    } else {
      synchronized(this) {
        if(_recipesDao == null) {
          _recipesDao = new RecipesDao_Impl(this);
        }
        return _recipesDao;
      }
    }
  }
}
