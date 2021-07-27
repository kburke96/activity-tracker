import { React } from 'react';
import './ActivitySmallCard.scss';

export const ActivitySmallCard = ({activity}) => {
    if (!activity) {
        return (
            <p>Activity not found.</p>
        );
    }
        
    return (
        <div className="ActivitySmallCard">
            <p>{activity.activityType}</p>
            <p>{activity.activityName}</p>
            <p>{activity.time}</p>
        </div>
    );
}