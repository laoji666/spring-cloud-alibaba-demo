package com.laoji.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件信息
 * <p>
 * Description:
 * </p>
*  @author: laoji
*  @date:2020/4/8 18:00
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {

    private static final long serialVersionUID = -1604422832059276517L;
    /**
     * 文件路径
     */
    private String path;
}
