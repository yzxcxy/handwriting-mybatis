package com.fqc.mapping;

import com.fqc.session.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 结果类型映射：
 * 即使是resultType，也会生成一个resultMap
 */
public class ResultMap {
    private String id;
    private Class<?> type;

    /**
     * TODO：
     * 暂时没有对于resultMap标签的处理
     */
    private List<ResultMapping> resultMappings;
    private Set<String> mappedColumns;

    private ResultMap() {
    }

    public static class Builder {
        private ResultMap resultMap = new ResultMap();

        public Builder(Configuration configuration, String id, Class<?> type, List<ResultMapping> resultMappings) {
            resultMap.id = id;
            resultMap.type = type;
            resultMap.resultMappings = resultMappings;
        }

        public ResultMap build() {
            //TODO
            resultMap.mappedColumns = new HashSet<>();
            return resultMap;
        }

    }

    public String getId() {
        return id;
    }

    public Set<String> getMappedColumns() {
        return mappedColumns;
    }

    public Class<?> getType() {
        return type;
    }

    public List<ResultMapping> getResultMappings() {
        return resultMappings;
    }
}
