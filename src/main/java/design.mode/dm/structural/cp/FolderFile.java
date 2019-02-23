package design.mode.dm.structural.cp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 11:27
 */
public class FolderFile implements IFile{

    List<IFile> files =  new ArrayList();
    private String name;

    public FolderFile(String name) {
        this.name = name;
    }

    @Override
    public void delete() {

    }

    @Override
    public String getName() {

        return null;
    }

    @Override
    public void createFile(String fileName) {
        if(fileName != null && fileName.length() > 0){
            IFile folder =  new FolderFile(fileName);
            files.add(folder);
        }
    }

    @Override
    public void deleteFile(String fileName) {
       files =  files.stream().filter(x -> !x.getName().equals(fileName)).collect(Collectors.toList());
    }

    @Override
    public IFile getIFile(int index) {
        if(index < files.size()){
            return files.get(index);
        }
        return null;
    }
}
