/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at http://sourceforge.net/projects/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * Agfa-Gevaert AG.
 * Portions created by the Initial Developer are Copyright (C) 2008
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See listed authors below.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */
package org.dcm4chee.web.war.tc;

import java.util.List;

import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * @author Bernhard Ableitinger <bernhard.ableitinger@agfa.com>
 * @version $Revision$ $Date$
 * @since Mar 31, 2013
 */
@SuppressWarnings("serial")
public class TCDetailsLinksTab extends TCDetailsTab {
	
    public TCDetailsLinksTab(final String id, 
    		TCAttributeVisibilityStrategy attrVisibilityStrategy) {
        super(id, attrVisibilityStrategy);

        add(new TCLinksView("tc-details-links", new AbstractReadOnlyModel<TCObject>() {
        	public TCObject getObject() {
        		return getTCObject();
        	}
        }, attrVisibilityStrategy, false));
    }
    
    @Override
    public boolean enabled() {
        return getLinkCount()>0;
    }
    
    @Override
    public boolean visible() {
    	return getAttributeVisibilityStrategy().isAttributeVisible(
    			TCAttribute.Links);
    }

    private TCObject getTCObject() {
        return (TCObject) getDefaultModelObject();
    }
    
    private int getLinkCount() {
    	int count = 0;
    	
        List<TCLink> links = getTCObject()!=null ? 
        		getTCObject().getLinks() : null;
        if (links!=null) {
        	for (TCLink link : links) {
        		if (link.isPermitted()) {
        			count++;
        		}
        	}
        }
        
        return count;
    }
}
