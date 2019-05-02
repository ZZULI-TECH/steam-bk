package org.steam.core.util;

import org.steam.common.exception.VersionException;
import org.steam.common.model.VersionEntity;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mingshan
 */
public class VersionUtil {

    /**
     * 检测版本号
     *
     * @param version 版本号
     * @param targetEntity 数据库中版本号
     * @throws VersionException
     */
    public static void checkVersion(long version, VersionEntity targetEntity) throws VersionException {
        Objects.requireNonNull(version, "版本号不能为空");
        Objects.requireNonNull(targetEntity, "实体为空");
        if (version != targetEntity.getVersion()) {
            throw new VersionException("版本号：" + version + "与版本号：" + targetEntity.getVersion() + "冲突");
        }
    }

    /**
     * 生成版本号
     *
     * @param sourceVersion 原版本号
     * @return 生成的版本号
     */
    public static long generateVersion(long sourceVersion) {
        final AtomicLong version = new AtomicLong(sourceVersion);
        return version.incrementAndGet();
    }
}
