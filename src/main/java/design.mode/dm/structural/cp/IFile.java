package design.mode.dm.structural.cp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 11:21
 */
public interface IFile {
    void delete();

    String getName();

    void createFile(String fileName);

    void deleteFile(String fileName);

    IFile getIFile(int index);

}
