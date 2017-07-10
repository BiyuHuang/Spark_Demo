package com.wallace.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wallace on 2017/2/26.
 */
public class ReloadThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(ReloadThread.class);

    private long lastSuccessfulReload;
    private File configureFile;
    public List<String> selectedFields;

    public ReloadThread(File configureFile) {
        this.configureFile = configureFile;
        selectedFields = new ArrayList<String>();
    }

    public String getSelectedFields() {
        StringBuilder sb = new StringBuilder();
        for (String item : selectedFields) {
            sb.append(item).append(",");
        }
        if (sb.length() > 0) {
            return (sb.substring(0, sb.length() - 1));
        } else {
            return "";
        }
    }

    @Override
    public void run() {
        while (true) {
            long time = System.currentTimeMillis();
            long lastModified = configureFile.lastModified();
            if (lastModified > lastSuccessfulReload
                    && time > lastModified) {
                try {
                    synchronized (selectedFields) {
                        reloadConfigureFile(configureFile);
                        log.info("Reload configuration file: " + getSelectedFields());
                        lastSuccessfulReload = System.currentTimeMillis();
                    }
                } catch (Exception ex) {
                    log.error(
                            "Failed to reload config file - will use existing configuration.",
                            ex);
                }
            }
        }
    }

    public void reloadConfigureFile(File configureFile) {
        try {
            /** 判断文件是否存在 */
            if (configureFile.isFile() && configureFile.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(configureFile));
                BufferedReader bufferedReader = new BufferedReader(read);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (!selectedFields.contains(line)) {
                        selectedFields.add(line);
                    }
                }
                read.close();
            } else {
                log.error("Failed to find file:" + configureFile.getAbsolutePath());
            }
        } catch (Exception e) {
            log.error("Failed to read file:" + configureFile.getAbsolutePath(), e);
        }
    }
}