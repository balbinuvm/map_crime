package com.example.map_crime;

import androidx.room.Entity;
import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {


        @TypeConverter
        public Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }
    }

