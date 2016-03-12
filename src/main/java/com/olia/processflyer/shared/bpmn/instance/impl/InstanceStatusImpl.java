/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.olia.processflyer.shared.bpmn.instance.impl;

import java.util.Date;
import java.util.Properties;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.instance.InstanceStatus;
import com.olia.processflyer.shared.bpmn.instance.InstanceStatusType;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class InstanceStatusImpl implements InstanceStatus, IsSerializable
{

    private String message;

    private InstanceStatusType status;

    private Date statusDate;

    InstanceStatusImpl(){}
    public InstanceStatusImpl(String message, InstanceStatusType status, Date statusDate)
    {
        super();
        this.message = message;
        this.status = status;
        this.statusDate = statusDate;
    }

    public static InstanceStatusImpl notExecutedState()
    {
        return new InstanceStatusImpl("this instance was not started yet", InstanceStatusType.None, new Date());
    }

    @Override
    public Date getStatusDate()
    {
        return statusDate;
    }

    @Override
    public InstanceStatusType getStatus()
    {
        return status;
    }

    @Override
    public String getMessage()
    {
        return message;
    }


}
