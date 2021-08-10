import './ActivityDetailCard.scss';
import { React } from 'react';
import { Link } from 'react-router-dom';

export const ActivityDetailCard = ({ activity }) => {

    return (
        <div className="ActivityDetailCard">
            <table className="activity-table">
                <tr>
                    <th>
                        <p className="date-label">Date: {activity.activityDate} </p>
                    </th>
                </tr>
                <tr>
                    <th>
                        <p className="distance-label">Distance: {activity.distance}km</p>
                    </th>
                    <th>
                        <p className="time-label">Time: {activity.time}</p>
                    </th>
                </tr>
            </table>
        </div>
    )
}