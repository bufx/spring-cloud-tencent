/*
 * Tencent is pleased to support the open source community by making Spring Cloud Tencent available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.tencent.cloud.metadata.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Haotian Zhang
 */
public class JacksonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtils.class);

    /**
     * Object Mapper
     */
    public static final ObjectMapper OM = new ObjectMapper();

    /**
     * Object to Json
     *
     * @param object
     * @param <T> type of object
     * @return Json String
     */
    public static <T> String serializeToJson(T object) {
        try {
            return OM.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("Object to Json failed. {}", object, e);
            throw new RuntimeException("Object to Json failed.", e);
        }
    }

    /**
     * Json to Map
     *
     * @param jsonStr Json String
     * @return Map
     */
    public static Map<String, String> deserializeMetadataMap(String jsonStr) {
        try {
            if (StringUtils.hasText(jsonStr)) {
                return OM.readValue(jsonStr, Map.class);
            }
            return new HashMap<>();
        } catch (JsonProcessingException e) {
            LOG.error("Json to map failed. check if the format of the json string[{}] is correct.", jsonStr, e);
            throw new RuntimeException("Json to map failed.", e);
        }
    }
}