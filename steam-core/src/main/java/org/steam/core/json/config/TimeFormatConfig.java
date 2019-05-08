// package org.steam.core.json.config;
//
// import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
// import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
// import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
// import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
// import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
// import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
// import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.LocalTime;
// import java.time.format.DateTimeFormatter;
//
// /**
//  * @author lyf
//  * @Description
//  * @date 2019-05-08 14:31
//  * @modified by
//  */
// @Configuration
// public class TimeFormatConfig {
//
//     @Bean
//     public LocalDateTimeSerializer localDateTimeSerializer() {
//         return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//     }
//
//     @Bean
//     public LocalDateTimeDeserializer localDateTimeDeserializer() {
//         return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//     }
//
//
//     @Bean
//     public LocalDateSerializer localDateSerializer(){
//         return new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//     }
//
//     @Bean
//     public LocalDateDeserializer localDateDeserializer() {
//         return new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//     }
//
//     @Bean
//     public LocalTimeSerializer localTimeSerializer(){
//         return new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss"));
//     }
//
//     @Bean
//     public LocalTimeDeserializer localTimeDeserializer() {
//         return new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss"));
//     }
//
//     @Bean
//     public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//         return builder -> builder
//                 .serializerByType(LocalDateTime.class, localDateTimeSerializer())
//                 .serializerByType(LocalTime.class,localTimeSerializer())
//                 .serializerByType(LocalDate.class,localDateSerializer())
//                 .deserializerByType(LocalDateTime.class,localDateTimeDeserializer())
//                 .deserializerByType(LocalTime.class,localTimeDeserializer())
//                 .deserializerByType(LocalDate.class,localDateDeserializer());
//     }
// }
