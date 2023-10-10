package org.macnss.Services;

import org.macnss.DAO.impl.FolderDAO;
import org.macnss.Entities.Folder;


import java.util.List;

public class FolderService implements Service<Folder>{

    private final FolderDAO folderDao = new FolderDAO();

    @Override
    public Folder save(Folder folder) {
        if (folderDao.save(folder) != null){
            return folder;
        }else {
            return null;
        }
    }

    @Override
    public Folder findBy(String folderId) {
        if (folderDao.findBy(folderId) != null){
            return folderDao.findBy(folderId);
        }else  {
            return  null;
        }
    }

    public List<Folder> getAll(){
        return folderDao.getAll();
    }

    @Override
    public boolean deactivate(String slag) {
        return false;
    }

    @Override
    public Folder update(Folder folder) {
        return null;
    }


}
