/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.urtsi.internal;

import static org.openhab.binding.urtsi.UrtsiBindingConstants.RTS_DEVICE_THING_TYPE;
import static org.openhab.binding.urtsi.UrtsiBindingConstants.URTSI_DEVICE_THING_TYPE;

import java.util.List;

import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.urtsi.handler.RtsDeviceHandler;
import org.openhab.binding.urtsi.handler.UrtsiDeviceHandler;
import org.osgi.service.component.annotations.Component;

import com.google.common.collect.Lists;

/**
 * The {@link UrtsiHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Oliver Libutzki - Initial contribution
 */
@Component
public class UrtsiHandlerFactory extends BaseThingHandlerFactory implements org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory{

    private final static List<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Lists.newArrayList(URTSI_DEVICE_THING_TYPE,
            RTS_DEVICE_THING_TYPE);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {

        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(URTSI_DEVICE_THING_TYPE) && thing instanceof Bridge) {
            return new UrtsiDeviceHandler((Bridge) thing);
        } else if (thingTypeUID.equals(RTS_DEVICE_THING_TYPE)) {
            return new RtsDeviceHandler(thing);
        }

        return null;
    }
}
