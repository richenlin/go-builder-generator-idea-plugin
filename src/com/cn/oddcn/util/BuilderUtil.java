package com.cn.oddcn.util;

import com.cn.oddcn.entity.StructEntity;

import java.util.List;
import java.util.Map;

public class BuilderUtil {

    public static String generateBuilderPatternCode(List<StructEntity> structEntityList) {
        StringBuilder strBuilder = new StringBuilder();

        for (StructEntity structEntity : structEntityList) {
            String structName = structEntity.structName;
            String lowerStructName = toLowerCase(structEntity.structName);

            strBuilder.append("\n\n// ");
            strBuilder.append(structName);
            strBuilder.append(" Builder pattern code\n");

            strBuilder.append("type ");
            strBuilder.append(structName);
            strBuilder.append("Builder struct {\n\tparams *");
            strBuilder.append(structName);
            strBuilder.append("\n}\n\n");

            strBuilder.append("func New");
            strBuilder.append(structName);
            strBuilder.append("Builder() *");
            strBuilder.append(structName);
            strBuilder.append("Builder {\n\t");
            strBuilder.append(lowerStructName);
            strBuilder.append(" := &");
            strBuilder.append(structName);
            strBuilder.append("{}\n\t");
            strBuilder.append(lowerStructName);
            strBuilder.append("Builder := &");
            strBuilder.append(structName);
            strBuilder.append("Builder{params: ");
            strBuilder.append(lowerStructName);
            strBuilder.append("}\n\treturn ");
            strBuilder.append(lowerStructName);
            strBuilder.append("\n}\n\n");

            for (Map.Entry<String, String> entry : structEntity.structKeyValue.entrySet()) {
                String keyName = entry.getKey();
                String lowerKeyName = toLowerCase(keyName);
                String typeName = entry.getValue();
                strBuilder.append("func (b *");
                strBuilder.append(structName);
                strBuilder.append("Builder");
                strBuilder.append(") ");
                strBuilder.append(keyName);
                strBuilder.append("(");
                strBuilder.append(lowerKeyName);
                strBuilder.append(" ");
                strBuilder.append(typeName);
                strBuilder.append(") *");
                strBuilder.append(structName);
                strBuilder.append("Builder {\n\tb.params.");
                strBuilder.append(keyName);
                strBuilder.append(" = ");
                strBuilder.append(lowerKeyName);
                strBuilder.append("\n\treturn b\n}\n\n");
            }

            strBuilder.append("func (b *");
            strBuilder.append(structName);
            strBuilder.append("Builder) Build() {\n\treturn\n}\n");
        }
        return strBuilder.toString();
    }

    private static String toLowerCase(String str) {
        char[] cs = str.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }
}
