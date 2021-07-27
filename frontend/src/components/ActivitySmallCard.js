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

                <p>{activity.activityType}</p>
                <p>{activity.activityName}</p>
                <p>{activity.time}</p>
            </div>
        </div>
    );
}