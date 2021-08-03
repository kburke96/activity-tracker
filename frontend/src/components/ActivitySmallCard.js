import { React } from 'react';
import './ActivitySmallCard.scss';

export const ActivitySmallCard = ({ activity }) => {
    

    if (!activity) {
        return (
            <p>Activity not found.</p>
        );
    }


    const isActivityCycling = activity.activityType === "Cycling";
    return (
        <div className="ActivitySmallCard">
            <div className={isActivityCycling ? 'cycling' : 'not-cycling'}>
                <table className="activity-table">
                    <tr>
                        <th>
                            <p className="activity-type-name">{activity.activityType}</p>
                        </th>
                        <th>
                            <p className="activity-distance">{activity.distance}km</p>
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <p className="activity-name">{activity.activityName}</p>
                            
                        </th>
                        <th>
                            <p className="activity-time">{activity.time}</p>
                        </th>
                    </tr>
                </table>
                
                
                
            </div>
        </div>
    );
}