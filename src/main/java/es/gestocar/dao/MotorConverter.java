
package es.gestocar.dao;

import es.gestocar.beans.Vehiculo;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author javier
 */
public class MotorConverter implements Converter {
    
    @Override
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            String strValue = (String) value;
            try {
                return Vehiculo.Motor.valueOf(strValue.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ConversionException("Invalid value for Motor: " + strValue);
            }
        }

        throw new ConversionException("Can't convert value '" + value + "' to type " + type);
    }
}
