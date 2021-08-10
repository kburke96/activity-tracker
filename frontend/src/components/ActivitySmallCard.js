import { React } from 'react';
import './ActivitySmallCard.scss';
import { Link } from 'react-router-dom';

export const ActivitySmallCard = ({ activity }) => {
    

    if (!activity) {
        return (
            <p>Activity not found.</p>
        );
    }

    var activityDateParts = activity.activityDate.split(" ");
    var activityDate = activityDateParts[0];
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
                            <p className="activity-name"><Link to={{pathname: `/activity/${activity.id}`}}>{activity.activityName} - {activityDate}</Link></p>
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