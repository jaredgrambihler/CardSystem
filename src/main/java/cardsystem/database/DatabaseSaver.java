package cardsystem.database;

public interface DatabaseSaver {

    /** Persists the current state of the object to the db. **/
    void saveToDatabase();
}
