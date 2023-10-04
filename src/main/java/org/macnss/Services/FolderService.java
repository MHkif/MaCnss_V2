package org.macnss.Services;

import org.macnss.dao.impl.DocumentDAO;
import org.macnss.dao.impl.FolderDAO;
import org.macnss.entity.Folder;

import java.sql.SQLException;
import java.util.List;

public class FolderService {
    private final FolderDAO folderDao = new FolderDAO();
    private final DocumentDAO documentDao = new DocumentDAO();

    public Folder create(Folder folder){
        if (folderDao.insert(folder) != null){
            return folder;
        }else {
            return null;
        }
    }

    public List<Folder> getAll() throws SQLException {

        return folderDao.getAll();
    }
    public Folder get(String folderId) throws SQLException {

        if (folderDao.get(folderId) != null){
            return folderDao.get(folderId);
        }else  {
            return  null;
        }


    }


}
